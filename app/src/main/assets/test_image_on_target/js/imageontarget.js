var previousRotationValue = [];
var previousScaleValue = [];

var oneFingerGestureAllowed = false;

AR.context.on2FingerGestureStarted = function() {
    oneFingerGestureAllowed = false;
};

/**-------------------client side---------------------------- ***/


// test image
var imageres = new AR.ImageResource("assets/solar_system_test_image.jpg");

var imageDrawable = new AR.ImageDrawable(imageres, 2, {
  translate : { x: 0 },
  rotate : { z: 0 },
  onDragBegan: function( /*x, y*/ ) {
      oneFingerGestureAllowed = true;

      return true;
  },
  onDragChanged: function(x, y, intersectionX, intersectionY) {
      if (oneFingerGestureAllowed) {
          this.translate = {
              x: intersectionX,
              y: intersectionY
          };
      }

      return true;
  },
  onDragEnded: function( /*x, y*/ ) {
      return true;
  },
  onRotationBegan: function( /*angleInDegrees*/ ) {
      return true;
  },
  onRotationChanged: function(angleInDegrees) {
      rotate.z = previousRotationValue + angleInDegrees;

      return true;
  },
  onRotationEnded: function( /*angleInDegrees*/ ) {
      previousRotationValue = rotate.z;

      return true;
  },
  onScaleBegan: function( /*scale*/ ) {
      return true;
  },
  onScaleChanged: function(scale) {
      var scaleValue = previousScaleValue * scale;
      scale = {
          x: scaleValue,
          y: scaleValue
      };

      return true;
  },
  onScaleEnded: function( /*scale*/ ) {
      previousScaleValue = scale.x;

      return true;
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