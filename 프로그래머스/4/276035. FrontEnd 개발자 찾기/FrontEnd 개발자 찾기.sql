# 9시 45분 ~
# 스킬의 코드는 2진수로 표현했을 때 각 bit로 구분될 수 있도록 2의 제곱수로 구성되어 있습니다.

select distinct d.id 'id', d.email 'email', d.first_name 'first_name', d.last_name 'last_name'
from developers d, skillcodes s
where d.skill_code & s.code != 0
    and s.category = 'Front End'
order by id asc
;