select e
from employee e 
join e.qualification q
join q.ProductType pt
join pt.product p
join p.ProductProcessor pp
join pp.machine m
join m.factory f
where m.code="given machine code" and
q.level = "given qualification level"
and f.address= "given factory address";