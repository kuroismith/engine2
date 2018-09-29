package org.engine2.core.model

case class ImageCollectionId(name: String) {
  override def toString: String = s"""ImageCollectionId(name = "$name")"""
}
