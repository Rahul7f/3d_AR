/**-------------------client side---------------------------- ***/


// test image
var imageres = new AR.ImageResource("assets/solar_system_test_image.jpg");

var imageDrawable = new AR.ImageDrawable(imageres, 2, {
  translate : { x: 1 },
  rotate : { z: 0 },
  onClick : function() {
      // 'this' represents the ImageDrawable
      this.rotate.z += 10;
    }
});

// test image end

// test object

var targetCollectionResource = new AR.TargetCollectionResource("assets/test_target_image.wtc");
var tracker = new AR.ImageTracker(targetCollectionResource);
// a ImageTrackable using the "car" target in the tracker, using the circle as the digital representation.
var ImageTrackable = new AR.ImageTrackable(tracker, "*", {
    drawables : {
        cam : imageDrawable
    }
});

/**-------------------client side end---------------------------- ***/


/**-------------------cloud side---------------------------- ***/




/**-------------------cloud side end---------------------------- ***/