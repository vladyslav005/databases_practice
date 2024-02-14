

with help_query as (
	select p.id as id, p.name as name, 
	(extract(year from p.finish_date) * 12 + extract(month from p.finish_date)) - 
		
	(extract(year from p.start_date) * 12 + extract(month from p.start_date))  
	
	
	as date_diff 
	from project p

) 

select h.id, h.name, h.date_diff from help_query h where h.date_diff = (select max(date_diff) from help_query) 
group by h.id, h.name, h.date_diff;