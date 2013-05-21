This implements a basic parcel service. A local server can be started with 'mvn jetty:run'.
All paths below assumes that the server is started with maven. If the war file is deployed
somewhere else, the base url will probably change.

The server consists of a http based api and a web page. The web page lists the existing
parcels and lets the user change the status. The web page can be reached by

	http://localhost:8080/
	
(Or you can add index.html at the end if you want to.)

The rest api has function for handling parcels and users.

Model
=====

A parcel has the following fields:
* id - String
* sender - String
* receiver - String
* weight - Integer
* status - SUBMITTED, SENT, or DELIVERED

A user has the following fields:
* id - String
* watchedParcelIds - Array of Strings

Parcel handling methods
=======================

* Get all parcels: GET http://localhost:8080/parcels
* Get one parcel: GET http://localhost:8080/parcels/{id}
* Change a parcel: PUT or POST http://localhost:8080/parcels/{id}
  The data posted is a URL encoded string with the fields to change.
  (E.g. 'sender=Pruseluskan&weight=10')
  
User handling methods
=====================

* Get all users: GET http://localhost:8080/users
* Get one user: GET http://localhost:8080/users/{id}
* Create a user: POST http://localhost:8080/users/{id}
* Let a user watch a parcel: POST http://localhost:8080/users/{id}/watch
  The data posted is a URL-encoded string:
  parcelid={parcel-id-to-watch}

Detailed description of api
===========================
* Get a list of all parcels:
Method: GET
URL: http://localhost:8080/api/parcels
Example response:

[
    {
        "id": "001",
        "sender": "Albin",
        "receiver": "Bertil",
        "weight": 4,
        "status": "SUBMITTED"
    },
    {
        "id": "002",
        "sender": "Cecilia",
        "receiver": "David",
        "weight": 1,
        "status": "SUBMITTED"
    },
    {
        "id": "003",
        "sender": "Erika",
        "receiver": "Fredrik",
        "weight": 19,
        "status": "SUBMITTED"
    }
]

* Get a specific parcel:
Method: GET
URL: http://localhost:8080/api/parcels/{parcel-id}
Example response:

{
    "id": "002",
    "sender": "Cecilia",
    "receiver": "David",
    "weight": 1,
    "status": "SUBMITTED"
}

* Change a parcel:
Method: PUT or POST
URL: http://localhost:8080/api/parcels/{parcel-id}
Data sent is a url-encoded string with the fields to change. (The id can't be changed).
Always returns an empty response.

Example data sent:
sender=Banarne&receiver=Trazan&status=SENT

* Get all users:
Method: GET
URL: http://localhost:8080/api/users
Example response:
[
    {
        "id": "Albin",
        "watchedParcelIds": [
            "001",
            "003"
        ]
    }
]

* Get a specific user:
Method GET
URL: http://localhost:8080/api/users/{id}
Example response:
{
    "id": "Albin",
    "watchedParcelIds": [
        "001",
        "003"
    ]
}

* Create a new user:
Method: POST
URL: http://localhost:8080/api/users/{id}
(No data posted)
Example response:
{
    "id": "Albin",
    "watchedParcelIds": []
}

* Let a user watch a parcel:
Method: POST
URL: http://localhost:8080/api/users/{id}/watch
Data posted is url-encoded 'parcelid={parcel-id-to-watch}
Always empty response.