select book_id 'BOOK_ID', date_format(published_date, "%Y-%m-%d") 'PUBLISHED_DATE'
from book
where date_format(published_date, "%Y")='2021' and category = '인문'
order by published_date asc
;
