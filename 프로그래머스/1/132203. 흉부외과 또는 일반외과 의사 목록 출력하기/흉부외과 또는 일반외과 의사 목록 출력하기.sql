-- 코드를 입력하세요
select DR_NAME, DR_ID, MCDP_CD, DATE_FORMAT(HIRE_YMD, "%Y-%m-%d") 'HIRE_YMD'
from doctor
where mcdp_cd in ('CS', 'GS')
order by hire_ymd desc, dr_name asc
;