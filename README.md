# showtrackerclj

The Show Tracker Web App in Clojure form! Uses sqlkorma with pgsql. UI is in Ember.js.

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Setup

Postgres with these columns:

    id serial NOT NULL primary key
    title character varying
    episode smallint
    
dbconfig.json with something like this:

    {
      "host" : "localhost",
      "port" : "5432",
      "user" : "pg-user",
      "password" : "mycrappypassword",
      "db" : "showtrackerdb"
    }


## Running

To start a web server for the application, run:

    lein ring server

## License

Copyright © 2014 FIXME
