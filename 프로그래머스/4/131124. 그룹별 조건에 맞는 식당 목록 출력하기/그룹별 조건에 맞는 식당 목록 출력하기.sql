# 7시 30분~
# - 고객 정보 (MEMBER_PROFILE)
# MEMBER_ID, MEMBER_NAME, TLNO(회원 연락처), GENDER, DATE_OF_BIRTH

# - 식당 리뷰 정보 (REST_REVIEW)
# REVIEW_ID, REST_ID(식당id), MEMBER_ID, REVIEW_SCORE, REVIEW_TEXT, REVIEW_DATE

SELECT P.MEMBER_NAME 'MEMBER_NAME', R.REVIEW_TEXT 'REVIEW_TEXT', 
        DATE_FORMAT(R.REVIEW_DATE, "%Y-%m-%d") 'REVIEW_DATE'
FROM MEMBER_PROFILE P
LEFT JOIN REST_REVIEW R ON P.MEMBER_ID = R.MEMBER_ID
WHERE P.MEMBER_ID = (SELECT MEMBER_ID
                    FROM REST_REVIEW
                    GROUP BY MEMBER_ID
                    ORDER BY COUNT(*) DESC 
                    LIMIT 1
                   )
ORDER BY REVIEW_DATE ASC, REVIEW_TEXT ASC
;



