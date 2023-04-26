# Cinema Manager API  
This is a simple REST API to handle Cinema operations. 
Application allows users to view available seats, book tickets, and get statistical information about the cinema.  
The application uses Spring, Spring MVC, Lombok, custom http responses (exceptions).  
The system is build without a database and utilises mapping java objects to JSON.

* Cinema room size is hard-coded to 9 rows with 9 seats to simplify application.
* Seats at the front of the cinema are more expensive than those at the back.

## How to Use:
- Clone the repository to your local machine.  
- Run the application in your IDE using Maven.
- Use Postman or other API client to interact with API endpoints.  
- Application runs on a port 8080, so the address to access endpoints starts with:  
  `http://localhost:8080/`

## Available endpoints:

### GET /seats
Returns a JSON object containing information about the seats available in the cinema.

### POST /purchase
Allows the user to purchase a ticket in the selected row and column.  
Requires the following JSON object as a request body:

```json
{
    "row": 1,
    "column": 1
}
```  
This will book the seat at row 1, column 1 and return a JSON object containing the ticket information,  
and unique UUID token that you need to pass if you return the ticket.

### POST /return
Allows the user to return a ticket. Requires the following JSON object as a request body:  

```json
{
    "token": "your_unique_token"
}
```  
This will return the ticket associated with the provided token and release the seat for others to book.

### POST /stats
Returns a JSON object containing statistical information about the cinema.  
Requires a query parameter password with the value of super_secret to be included in the URL, like this: 

`http://localhost:8080/stats?password=super_secret`  

This endpoint will return a JSON object containing the following information:

- Total number of purchased tickets
- Percentage of seats that have been booked
- Current income of the cinema
- Potential income of the cinema
