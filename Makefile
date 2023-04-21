

dev-migrate:
	docker-compose exec pgdb0 psql -U test -p 5432 -d test_db -f /sql/schema.sql
	docker-compose exec pgdb1 psql -U test -p 5432 -d test_db -f /sql/schema.sql
