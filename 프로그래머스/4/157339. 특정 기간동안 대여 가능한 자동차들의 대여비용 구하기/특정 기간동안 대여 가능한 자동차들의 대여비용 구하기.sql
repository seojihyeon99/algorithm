# 대여 중인 자동차 : CAR_RENTAL_COMPANY_CAR 
#                     CAR_ID, CAR_TYPE(세단, SUV, ..), DAILY_FEE, OPTIONS(열선시트, 스마트키, ..)
# 자동차 대여 기록 : CAR_RENTAL_COMPANY_RENTAL_HISTORY
#                     HISTORY_ID, CAR_ID, START_DATE, END_DATE
# 자동차 종류 별 대여 기간 종류 별 정책 : CAR_RENTAL_COMPANY_DISCOUNT_PLAN
#                     PLAN_ID, CAR_TYPE, DURATION_TYPE(7일 이상, 30일 이상, 90일 이상), DISCOUNT_RATE


SELECT *
FROM (
    SELECT C.CAR_ID, C.CAR_TYPE, ROUND(C.DAILY_FEE*(1-DISCOUNT_RATE/100)*30) 'FEE'
    FROM CAR_RENTAL_COMPANY_CAR C
    LEFT JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN P ON C.CAR_TYPE = P.CAR_TYPE
    WHERE C.CAR_TYPE IN ('세단', 'SUV')
        AND C.CAR_ID NOT IN (
                                SELECT DISTINCT CAR_ID
                                FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY H
                                WHERE H.END_DATE >= '2022-11-01' AND H.START_DATE <= '2022-11-30'
                            )
        AND P.DURATION_TYPE = '30일 이상'
    ) R
WHERE R.FEE >= 500000 AND R.FEE < 2000000
ORDER BY FEE DESC, CAR_TYPE ASC, CAR_ID DESC
;