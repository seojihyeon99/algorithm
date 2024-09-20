# 7시 10분~
# - 상반기 주문 정보 (FIRST_HALF)
# SHIPMENT_ID(FK), FLAVOR(PK), TOTAL_ORDER

# - 7월 주문 정보 (JULY)
# SHIPMENT_ID(PK), FLAVOR(FK), TOTAL_ORDER

# 7월 주문량이 많으면, 같은 아이스크림에 대해서 서로 다른 두 공장에서 아이스크림 가게로 출하 진행(=출하 번호 다름)

SELECT FLAVOR
FROM (
        SELECT FH.FLAVOR, (FH.TOTAL_ORDER + J.TOTAL_ORDER) 'TOTAL'
        FROM FIRST_HALF FH
        LEFT JOIN (
                    SELECT FLAVOR, SUM(TOTAL_ORDER) 'TOTAL_ORDER'
                    FROM JULY
                    GROUP BY FLAVOR
                  ) J
        ON FH.FLAVOR = J.FLAVOR
        ORDER BY TOTAL DESC
    ) R
LIMIT 3
;