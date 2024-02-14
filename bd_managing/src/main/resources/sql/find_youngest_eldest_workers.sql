select 'OLDEST' as type, w.* from worker w where w.birthday = (select min(birthday) from worker)
union
select 'YOUNGEST' as type, w.* from worker w where w.birthday = (select max(birthday) from worker); 
