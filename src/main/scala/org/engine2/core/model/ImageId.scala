package org.engine2.core.model

case class ImageId(name: String) {
  override def toString: String = s"""ImageId(name = "$name")"""
}
