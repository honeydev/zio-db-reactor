package db.reactor.common.dto

abstract class AbstractSourceCollection(val name: String)
abstract class AbstractSource(val sourceName: String, val collections: List[AbstractSourceCollection])
