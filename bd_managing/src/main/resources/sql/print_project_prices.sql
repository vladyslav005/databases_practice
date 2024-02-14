
with hq1 as (

select p.id as ID, p.name as NAME, sum(w.salary) as price, 
(extract(year from p.finish_date) * 12 + extract(month from p.finish_date)) - 		
(extract(year from p.start_date) * 12 + extract(month from p.start_date)) as date_diff 
from project p 
	left join worker w on exists (select from project_worker pw where pw.project_id = p.id and pw.worker_id = w.id)
group by p.id, p.name

)

select h.name, h.price * h.date_diff from hq1 h;



