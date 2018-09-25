package org.engine2.core.ast

case class Function(
  argumentNames: List[String],
  body: ValueRef
) extends Expr with Statement