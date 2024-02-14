with help_query as (
    select c.id as client_id, c.name as client_name, count(p.client_id) as count_of_entries
    from client c
             left join project p on c.id = p.client_id
    GROUP BY
        c.ID, c.NAME
)

select h.client_id, h.client_name, h.count_of_entries from help_query h
    where h.count_of_entries = (select max(count_of_entries) from help_query)
    group by h.client_id, h.client_name, h.count_of_entries;