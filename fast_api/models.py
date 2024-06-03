from sqlalchemy import Column, Integer, String, Float

from database import Base


class Region(Base):
    __tablename__ = "region"

    region_id = Column(Integer, primary_key=True)
    area = Column(Integer)
    household = Column(Integer)
    province = Column(String)
    region_name = Column(String)
    returners = Column(Integer)
    image_url = Column(String)
    child_care_facilities = Column(Float)
    average_accesstime_educational_facilities = Column(Float)
    average_price_farmland = Column(Float)
    average_housing_price = Column(Float)
    average_accesstime_amenities = Column(Float)
    population_density = Column(Float)
    dust_concentration = Column(Float)
    water_quality = Column(Float)
    education_cluster = Column(Integer)
    ground_cluster = Column(Integer)
    resident_cluster = Column(Integer)
    env_cluster = Column(Integer)
    elementary_school = Column(Integer)
    kindergarden = Column(Integer)
    middle_school = Column(Integer)
    pediatrics = Column(Integer)
    average_accesstime_medical_facilities = Column(Float)
    average_accesstime_sales_facilities = Column(Float)
    average_accesstime_traffic_facilities = Column(Float)