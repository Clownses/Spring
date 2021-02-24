# Test Spring web application for loading data from MS Sql server

## Endpoints
### Show all bills
GET `/bills`

### Show single bill
GET `/bills/{id}`

## Mysql Stored Procedures
### Get single bill
> SET ANSI_NULLS ON
> GO
> SET QUOTED_IDENTIFIER ON
> GO
> CREATE PROCEDURE getBill
> 	@id int = 0
> AS
> BEGIN
> 	SET NOCOUNT ON;
> 	SELECT * FROM bills WHERE id = @id LIMIT 1;
> END
> GO

### Get all bills
> SET ANSI_NULLS ON
> GO
> SET QUOTED_IDENTIFIER ON
> GO
> CREATE PROCEDURE getAllBills
> AS
> BEGIN
> 	SET NOCOUNT ON;
> 	SELECT * FROM bills;
> END
> GO