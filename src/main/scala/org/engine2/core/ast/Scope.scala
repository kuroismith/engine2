package org.engine2.core.ast

import scala.collection.mutable

class Scope(val name: String) {
  var values = new mutable.HashMap[String, Expr]
}
