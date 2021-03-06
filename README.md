# Test Spring web application for loading data from MS Sql server

## Endpoints
### Show all bills
[GET](GET) `/bills`

```
[
    [
        1,
        619742.6,
        "UAH",
        "1976-03-18T14:59:15",
        "c2835090-8903-4e6b-963c-c4185de28d0e"
    ],
    [
        ...
    ],
]
```

### Show single bill
[GET](GET) `/bills/{id}`

```
[
    [
        1,
        1234.6,
        "USD",
        "2020-04-12T12:07:00",
        "c2as309f-8520-3j5h-963c-c0785as46f0e"
    ]
]
```

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