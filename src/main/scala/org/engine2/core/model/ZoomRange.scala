package org.engine2.core.model

case class ZoomRange(minZoom: Int, maxZoom: Int) {
  override def toString: String = s"ZoomRange(minZoom = $minZoom, maxZoom = $maxZoom)"

  def contain(zoom: Int): Boolean = zoom >= minZoom && zoom <= maxZoom
}
