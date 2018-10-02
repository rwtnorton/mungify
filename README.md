# mungify

A commandline app that reads Person records from `stdin`
and outputs these records to `stdout`, sorted in three ways.

## Usage

```
  $ cat resources/sample-data-comma |lein run
  $ cat resources/sample-data-vbar |lein run
  $ cat resources/sample-data-space |lein run
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
