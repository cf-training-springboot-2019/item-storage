## Item Storage

## Purpose

The Item Storage microservice is responsible for the provisioning and management of every item resources and their respective workflows, fundamentally functioning as the source of truth for all item resources within this microservice based ecosystem.<br>
The component itself consists of a standalone Java Backend application which uses Spring Boot 2.2.0 RELEASE as its development foundation.

## Terminology
 | Term | Description |
 | ---- | --- |
 | Item| A service managed entity that represents a catalogued item currently being held in storage. Retains information exclusive to the item, such as the name, quantity available and price tag.
 
 ## Used Libraries 
  * spring-boot-starter-actuator
  * spring-boot-starter-data-jpa
  * spring-boot-starter-web 
  * spring-boot-starter-test
  * mysql-connector-java
  * h2
  * modelmapper
 
## Operations
 | Operation | Type | Description |
 | ---- | --- | --- |
| listItems |synchronous| An operation that returns a collection of persisted items. The collection is pageable.
| getItem |synchronous|An operation that returns a single persisted item.
| createItem |synchronous| An operation that persists a single item.
| updateItem |synchronous| An operation that updates a single item.
| deleteItem |synchronous| An operation that removes a single persisted item.
| dispatchItem |synchronous| An operation that dispatches a single item by the specified amount subtracting said value from it's previous quantity.
| restockItem |synchronous| An operation that restocks a single item by the specified amount adding said value to it's previous quantity.

## Provided Beans
 | Bean | Type | Scope | Description |
 | ---- | --- | --- |--- |
 | ItemService | Service | Singleton | This bean provides every feature available under the Geofence Restful API. 
 | ItemRepository | Repository | Singleton | This bean provides Fence persistence layer implementation.

## Miscellaneous Links
* [Release Notes](https://github.com/cf-training-springboot-2019/item-storage/tree/WEEK-1/CHANGELOG.md)