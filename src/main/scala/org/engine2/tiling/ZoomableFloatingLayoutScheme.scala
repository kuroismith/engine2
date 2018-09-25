package org.engine2.tiling

import geotrellis.raster.{CellSize, TileLayout}
import geotrellis.spark.tiling._
import geotrellis.vector.Extent

object ZoomableFloatingLayoutScheme {
  val DEFAULT_TILE_SIZE = 256

  def apply(baseResolution: Double): ZoomableFloatingLayoutScheme =
    apply(DEFAULT_TILE_SIZE, baseResolution)

  def apply(tileSize: Int, baseResolution: Double): ZoomableFloatingLayoutScheme =
    apply(tileSize, tileSize, baseResolution)

  def apply(tileCols: Int, tileRows: Int, baseResolution: Double): ZoomableFloatingLayoutScheme =
    new ZoomableFloatingLayoutScheme(tileCols, tileRows, baseResolution)

}

/**
  * 允许缩放的 FloatingLayoutScheme, 按照原始分辨率的 2 倍进行缩放
  * 基础分辨率对应 Zoom 0
  * ZoomOut 后, 分辨率的数值变为原来的 1/2, zoom -1
  * ZoomIn 后, 分辨率的数值变为原来的 2 倍, zoom +1
  *
  */
class ZoomableFloatingLayoutScheme(val tileCols: Int, val tileRows: Int, val baseResolution: Double) extends LayoutScheme {

  def zoom(resolution: Double): Int = {
    if (resolution <= 0)
      sys.error("resolution should be greater than 0")

    math.ceil(math.log(baseResolution / resolution) / math.log(2)).toInt
  }

  def resolutionForZoom(level: Int): Double = {
    if (level > 0)
      sys.error("zoomable floating scheme does not have levels above 0")

    math.pow(2, -level) * baseResolution
  }

  def layoutColsForZoom(extent: Extent, level: Int): Int = {
    math.ceil(extent.width / resolutionForZoom(level) / tileCols).toInt
  }

  def layoutRowsForZoom(extent: Extent, level: Int): Int = {
    math.ceil(extent.height / resolutionForZoom(level) / tileRows).toInt
  }

  def expandExtent(extent: Extent, zoom: Int): Extent = {
    val xmin = extent.xmin
    val ymin = extent.ymin
    val resolution = resolutionForZoom(zoom)
    val layoutCols = layoutColsForZoom(extent, zoom)
    val layoutRows = layoutColsForZoom(extent, zoom)
    val xmax = xmin + resolution * layoutCols * tileCols
    val ymax = ymin + resolution * layoutRows * tileRows
    new Extent(xmin = xmin, xmax = xmax, ymin = ymin, ymax = ymax)
  }

  def levelForZoom(extent: Extent, zoom: Int): LayoutLevel = {
    val e = expandExtent(extent, zoom)
    val layoutCols = layoutColsForZoom(e, zoom)
    val layoutRows = layoutRowsForZoom(e, zoom)
    LayoutLevel(zoom, LayoutDefinition(e, TileLayout(layoutCols, layoutRows, tileCols, tileRows)))
  }

  def levelFor(extent: Extent, cellSize: CellSize): LayoutLevel = {
    val l = zoom(cellSize.resolution)
    levelForZoom(extent, l)
  }

  def zoomOut(level: LayoutLevel): LayoutLevel = {
    val extent = level.layout.extent
    val l = level.zoom - 1
    levelForZoom(extent, l)
  }

  def zoomIn(level: LayoutLevel): LayoutLevel = {
    val extent = level.layout.extent
    val l = level.zoom + 1
    levelForZoom(extent, l)
  }
}
