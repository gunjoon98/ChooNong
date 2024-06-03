from sqlalchemy.orm import Session
from sqlalchemy import or_
import models


def get_region_list(db: Session, education_cluster: int = None, ground_cluster: int = None, residential_cluster: int = None, env_cluster_list: list = None):
    query = db.query(models.Region)
    if education_cluster is not None:
        query = query.filter(models.Region.education_cluster == education_cluster)
    if ground_cluster is not None:
        query = query.filter(models.Region.ground_cluster == ground_cluster)
    if residential_cluster is not None:
        query = query.filter(models.Region.resident_cluster == residential_cluster)
    if env_cluster_list is not None:
        if(len(env_cluster_list) == 2):
            query = query.filter(or_(models.Region.env_cluster == env_cluster_list[0], models.Region.env_cluster == env_cluster_list[1]))
        else:
            query = query.filter(models.Region.env_cluster == env_cluster_list[0])
    return query.all()

