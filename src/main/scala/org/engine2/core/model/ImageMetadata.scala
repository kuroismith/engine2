package org.engine2.core.model

import geotrellis.proj4.CRS
import geotrellis.raster.{CellType, TileLayout}
import geotrellis.spark.tiling.{LayoutDefinition, MapKeyTransform}
import geotrellis.spark.Bounds
import geotrellis.vector.Extent

case class ImageMetadata[K](
  id: ImageId,
  cellType: CellType,
  layout: LayoutDefinition,
  extent: Extent,
  crs: CRS,
  bounds: Bounds[K]
) {
  def mapTransform: MapKeyTransform = layout.mapTransform

  def tileLayout: TileLayout = layout.tileLayout

  def layoutExtent: Extent = layout.extent

  def gridBounds = mapTransform(extent)

}