package org.engine2.core.model.io

import geotrellis.proj4.CRS
import geotrellis.raster.CellType
import geotrellis.spark.tiling.LayoutDefinition
import geotrellis.spark.{Bounds, LayerId, SpatialKey, TileLayerMetadata}
import geotrellis.vector.Extent

// A Image is a list of tileLayerRDD with ImageMetadata
case class ImageMetadata[Z: Range](
  id: String,
  zoomRange: Range,
  override val cellType: CellType,
  override val layout: LayoutDefinition,
  override val extent: Extent,
  override val crs: CRS,
  override val bounds: Bounds[SpatialKey]
) extends TileLayerMetadata[SpatialKey](cellType, layout, extent, crs, bounds) {

  def layerId(zoom: Int): LayerId = {
    assert(zoomRange.contains(zoom), s"zoom should be within zoomRange: $zoomRange")
    LayerId(id, zoom)
  }
}