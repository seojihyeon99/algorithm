select fh.flavor
from first_half fh left join icecream_info ii
on fh.flavor = ii.flavor
where total_order >= 3000 and ingredient_type = 'fruit_based'
order by total_order desc
;