package org.engine2.core.ast

case class Invocation(
  functionName: String,
  arguments: Map[String, Expr]
) extends Expr with Statement
