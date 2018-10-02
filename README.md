# mungify

A commandline app that reads Person records from `stdin`
and outputs these records to `stdout`, sorted in three ways.

Additionally offers RESTful API to access Person records.
(Note: records are not persisted between the two use cases.)

## Usage

Commandline use:
```
  $ cat resources/sample-data-comma |lein run
  $ cat resources/sample-data-vbar |lein run
  $ cat resources/sample-data-space |lein run
```

Web use:
```
  $ lein ring server-headless
  ### then, from another shell session
  $ curl -i -X POST -H 'content-type: text/plain' \
      -d 'Hmm Orly female red 2000-01-01 ' http://localhost:3000/records
  $ curl http://localhost:3000/records/gender
  $ curl http://localhost:3000/records/birthdate
  $ curl http://localhost:3000/records/name
```

## Test

```
  $ lein test
```

## Local Development

```
  $ lein cljfmt fix && bin/run-checks
```

## License

Copyright © 2018 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
