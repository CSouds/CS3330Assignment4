# Enchanted Courier Guild

A small Java 17 Maven project for an object-oriented programming TDD assignment.

## Story

The Enchanted Courier Guild delivers magical packages across fantasy realms. A delivery plan chooses the correct courier creature, seals the package, calculates the coin price, records the delivery, and can notify the customer through a legacy owl-scroll system.

## Package layout

```text
edu.oop.guild.model          Domain objects and enums
edu.oop.guild.creature       Courier creature hierarchy
edu.oop.guild.strategy       Delivery pricing strategies
edu.oop.guild.seal           Package seal abstractions and implementations
edu.oop.guild.factory        Realm factories and factory provider
edu.oop.guild.log            Singleton guild log
edu.oop.guild.notification   Adapter around the legacy owl-scroll API
edu.oop.guild.service        Delivery planning service
```

## Design patterns included

- Strategy: delivery pricing policies
- Abstract Factory: realm-specific couriers and package seals
- Singleton: guild delivery log
- Adapter: modern notification channel around a legacy owl-scroll API

## Test style

The tests are intentionally written for a TDD assignment style:

- Each test method focuses on one observable behavior.
- Assertions are split so individual failures point to one expectation.
- Parameterized tests are used where the same behavior is checked with multiple inputs.
- JaCoCo is configured to require 100% line and instruction coverage.

Run tests and coverage:

```bash
mvn clean verify
```

Coverage output is generated at:

```text
target/site/jacoco/index.html
```
