# Issue Manager

Learn how to setup the project.

## Requirements

* chocolatey
* eclipse

## Install

On the command line:

    choco install gradle
    cd /issue-manager/
    gradle build
    gradle eclipse

Then import the project in eclipse

## Events

Reocurring events and their actions.

### DB Scheme Update

If the db scheme has been updated run this command.

    gradle flywayMigrate

