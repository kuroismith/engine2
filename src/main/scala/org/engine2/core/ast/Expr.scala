package org.engine2.core.ast

import scala.util.parsing.input.Positional

trait Expr extends Positional

case class Number(value: Double) extends Expr

case class String(value: String) extends Expr

case class Array[T <: Expr](values: List[T]) extends Expr

