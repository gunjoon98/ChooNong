from fastapi import Depends, FastAPI, HTTPException
from sqlalchemy.orm import Session

import crud, models, schemas
from database import SessionLocal, engine
from typing import List

import numpy as np
from numpy.linalg import norm
from numpy import dot
import pandas as pd
from sklearn.preprocessing import StandardScaler
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI()

origins = [
    'http://14.50.47.228:5173',
    '*'
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=False,
    allow_methods=["*"],
    allow_headers=["*"],
)

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

def cosine_similarity(A, B):
  return dot(A, B)/(norm(A)*norm(B))

app = FastAPI()

# 군집 지역 반환
def get_region_list(survey: schemas.Survey, db: Session = Depends(get_db)):
    education_cluster = None
    ground_cluster = None
    resident_cluster = None
    env_cluster_list = None

    # 1. 자녀가 있습니까?
    if survey.one == 1:
        education_cluster = 0

    # 2. 고려하는 예산을 선택해주세요.
    if survey.two == 1:
        ground_cluster = 0
    else:
        ground_cluster = 1

    # 3. 선호하는 자연 환경을 선택해주세요
    if survey.three[0] + survey.three[1] >= 8:
        env_cluster_list = [0]
    elif survey.three[0] > survey.three[1]:
        env_cluster_list = [0, 1]
    elif survey.three[0] < survey.three[1]:
        env_cluster_list = [0, 2]

    # 4. 어떤 귀농 생활을 꿈꾸시나요?
    if survey.four[0] == 1:
        resident_cluster = 0
    else:
        resident_cluster = 1

    region_list = crud.get_region_list(db, education_cluster, ground_cluster, resident_cluster, env_cluster_list)

    # 군집이 없으면 전체 지역 조회
    if (len(region_list) < 5):
        region_list = crud.get_region_list(db, None, None, None, None)

    return region_list


# 설문에 맞춰 차원 축소
def dimension_Reduction(survey: schemas.Survey, vector_df: pd.DataFrame):
    remove_columns = []
    if survey.one == 1:
        if survey.one_sub[0] == 1:
            remove_columns.append('elementary_school')
            remove_columns.append('middle_school')
        elif survey.one_sub[0] == 2:
            remove_columns.append('kindergarden')
            remove_columns.append('middle_school')
        else:
            remove_columns.append('kindergarden')
            remove_columns.append('elementary_school')

        if survey.one_sub[1] == 3:
            remove_columns.append('average_accesstime_educational_facilities')
    else:
        remove_columns.append('kindergarden')
        remove_columns.append('elementary_school')
        remove_columns.append('middle_school')
        remove_columns.append('average_accesstime_educational_facilities')
        remove_columns.append('pediatrics')
    return vector_df.drop(remove_columns, axis=1)


# 가중치 생성 및 기준 벡터 생성
def create_riterion_vector(survey: schemas, vector_df: pd.DataFrame, vector_nparr: np.ndarray):
    # 가중치 계산, 10퍼
    max_values = np.max(vector_nparr, axis=0)
    min_values = np.min(vector_nparr, axis=0)
    vector_weights = (max_values - min_values) / 10

    # 기준 벡터 생성
    riterion_vector = np.average(vector_nparr, axis=0)
    vector_df.columns = vector_df.columns.tolist()
    for i in range(len(vector_df.columns)):
        if vector_df.columns[i] == 'kindergarden':
            riterion_vector[i] += get_kindergarden_weight() * vector_weights[i]
        elif vector_df.columns[i] == 'elementary_school':
            riterion_vector[i] += get_elementary_school_weight() * vector_weights[i]
        elif vector_df.columns[i] == 'middle_school':
            riterion_vector[i] += get_middle_school_weight() * vector_weights[i]
        elif vector_df.columns[i] == 'average_accesstime_educational_facilities':
            riterion_vector[i] += get_average_accesstime_educational_facilities_weight(survey) * vector_weights[i]
        elif vector_df.columns[i] == 'pediatrics':
            riterion_vector[i] += get_pediatrics_weight(survey) * vector_weights[i]
        elif vector_df.columns[i] == 'water_quality':
            riterion_vector[i] += get_water_quality_weight(survey) * vector_weights[i]
        elif vector_df.columns[i] == 'dust_concentration':
            riterion_vector[i] += get_dust_concentration_weight(survey) * vector_weights[i]
        elif vector_df.columns[i] == 'average_accesstime_sales_facilities':
            riterion_vector[i] += get_average_accesstime_sales_facilities_weight(survey) * vector_weights[i]
        elif vector_df.columns[i] == 'average_accesstime_traffic_facilities':
            riterion_vector[i] += get_average_accesstime_traffic_facilities_weight(survey) * vector_weights[i]
    return riterion_vector


# 유치원 개수 가중치 계수 반환
def get_kindergarden_weight():
    return 2

# 초등학교 개수 가중치 계수 반환
def get_elementary_school_weight():
    return 2

# 중학교 개수 가중치 계수 반환
def get_middle_school_weight():
    return 2

# 교육 시설 접근 시간 가중치 계수 반환
def get_average_accesstime_educational_facilities_weight(survey: schemas.Survey):
    if survey.one_sub[1] == 1:
        return -2
    elif survey.one_sub[1] == 2:
        return -1

# 소아과 가중치 계수 반환
def get_pediatrics_weight(survey: schemas.Survey):
    if survey.one_sub[2] == 0:
        return -1
    elif survey.one_sub[2] == 1:
        return 1.5
    elif survey.one_sub[2] == 2:
        return 2
    elif survey.one_sub[2] == 3:
        return 2.5

# 수질 가중치 계수 반환
def get_water_quality_weight(survey: schemas.Survey):
    if survey.three[0] == 5:
        return -2
    elif survey.three[0] == 4:
        return -1
    elif survey.three[0] == 3:
        return 0
    elif survey.three[0] == 2:
        return 1
    elif survey.three[0] == 1:
        return 2

# 미세먼지 농도 가중치 계수 반환
def get_dust_concentration_weight(survey: schemas.Survey):
    if survey.three[1] == 5:
        return -2
    elif survey.three[1] == 4:
        return -1
    elif survey.three[1] == 3:
        return 0
    elif survey.three[1] == 2:
        return 1
    elif survey.three[1] == 1:
        return 2

# 판매시설 접근 시간 계수 반환
def get_average_accesstime_sales_facilities_weight(survey: schemas.Survey):
    if survey.four[1] == 1:
        return -2
    elif survey.four[1] == 2:
        return -1
    elif survey.four[1] == 3:
        return 1
    elif survey.four[1] == 4:
        return 2

# 교통시설 접근 시간 계수 반환
def get_average_accesstime_traffic_facilities_weight(survey: schemas.Survey):
    if survey.four[2] == 1:
        return -2
    elif survey.four[2] == 2:
        return -1
    elif survey.four[2] == 3:
        return 1
    elif survey.four[2] == 4:
        return 2


@app.post("/fapi/recommendation", response_model=List[schemas.Region])
async def recommend(survey: schemas.Survey, db: Session = Depends(get_db)):
    # 군집 지역 구하기
    region_list = get_region_list(survey, db)

    # df로 변환
    dic_list = []
    for region in region_list:
        dic = {}
        for column in region.__table__.columns:
            dic[column.name] = getattr(region, column.name)
        dic_list.append(dic)

    origin_df = pd.DataFrame(dic_list)
    vector_df = origin_df.drop(['region_id', 'area', 'household', 'province', 'region_name',
                                'returners', 'image_url', 'child_care_facilities', 'average_price_farmland',
                                'average_housing_price', 'average_accesstime_amenities','education_cluster',
                                'ground_cluster', 'resident_cluster', 'env_cluster', 'average_accesstime_medical_facilities'], axis=1)

    # 설문에 맞춰 차원 축소
    vector_df = dimension_Reduction(survey, vector_df)

    # 스케일링된 nparr 생성
    scaler = StandardScaler()
    vector_nparr = scaler.fit_transform(vector_df)

    # 기준 벡터 생성
    riterion_vector = create_riterion_vector(survey, vector_df, vector_nparr)

    # 유사도 계산, 랭킹
    result = []
    for idx, vector in enumerate(vector_nparr):
        df_item = origin_df.iloc[idx]
        result.append({"region_id": df_item["region_id"],
                       "region_name": df_item["region_name"],
                       "province": df_item["province"],
                       "cosine_similartiy": cosine_similarity(riterion_vector, vector)})

    sorted_result = sorted(result, key=lambda x: x['cosine_similartiy'], reverse=True)[:5]
    for item in sorted_result:
        del(item['cosine_similartiy'])
    return sorted_result