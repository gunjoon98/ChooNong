from pydantic import BaseModel, Field, field_validator
from typing import List


class Survey(BaseModel):
    one: int = Field(ge=1, le=2)
    one_sub: List[int] = None
    two: int = Field(ge=1, le=2)
    three: list[int]
    four: list[int]

    @field_validator('one_sub')
    def check_one_sub(cls, v):
        if len(v) == 0:
            v = None
            return v

        if len(v) != 3:
            raise ValueError('one_sub filed must have exactly 3 items')
        if v[0] < 1 or v[0] > 3:
            raise ValueError('one_sub[0] filed value error')
        if v[1] < 1 or v[1] > 3:
            raise ValueError('one_sub[1] filed value error')
        if v[2] < 1 or v[2] > 4:
            raise ValueError('one_sub[2] filed value error')
        return v

    @field_validator('three')
    def check_three(cls, v):
        if len(v) != 2:
            raise ValueError('three filed must have exactly 2 items')
        if v[0] < 1 or v[0] > 5:
            raise ValueError('three[0] filed value error')
        if v[1] < 1 or v[1] > 5:
            raise ValueError('three[1] filed value error')
        return v

    @field_validator('four')
    def check_four(cls, v):
        if len(v) != 3:
            raise ValueError('four filed must have exactly 3 items')
        if v[0] < 1 or v[0] > 2:
            raise ValueError('four[0] filed value error')
        if v[1] < 1 or v[1] > 4:
            raise ValueError('four[1] filed value error')
        if v[2] < 1 or v[2] > 4:
            raise ValueError('four[2] filed value error')
        return v


class Region(BaseModel):
    region_id: int
    region_name: str
    province: str
