// var World = {
//     loaded: false,
//     rotating: false,

//     init: function initFn() {
//         this.createOverlays();
//     },

//     createOverlays: function createOverlaysFn() {

//         this.targetCollectionResource = new AR.TargetCollectionResource("assets/test_target_image.wtc", {
//             onError: World.onError
//         });


        // this.tracker = new AR.ImageTracker(this.targetCollectionResource, {
        //     onTargetsLoaded: World.showInfoBar,
        //     onError: World.onError
        // });


        // this.modelCar = new AR.Model("assets/first.wt3", {
        //     onLoaded: World.showInfoBar,
        //     onError: World.onError,
        //     scale: {
        //         x: 0.045,
        //         y: 0.045,
        //         z: 0.045
        //     },
        //     translate: {
        //         x: 0.0,
        //         y: 0.05,
        //         z: 0.0
        //     },
        //     rotate: {
        //         z: -25
        //     }
        // });
        // this.trackable = new AR.ImageTrackable(this.tracker, "*", {
        //     drawables: {
        //         cam: [this.modelCar]
        //     },
        //     onImageRecognized: World.hideInfoBar,
        //     onError: World.onError
        // });
//     },

//     onError: function onErrorFn(error) {
//         alert(error);
//     },

// };

// World.init();

///----------------------------------

var targetCollectionResource = new AR.TargetCollectionResource("assets/test_target_image.wtc", {
    onError: alert("error")
});

var tracker = new AR.ImageTracker(targetCollectionResource, {
    onTargetsLoaded: alert("shoe some info"),
    onError: onError
});


var modelCar = new AR.Model("assets/first.wt3", {
    onLoaded: alert("show"),
    onError: alert("error on model"),
    scale: {
        x: 0.045,
        y: 0.045,
        z: 0.045
    },
    translate: {
        x: 0.0,
        y: 0.05,
        z: 0.0
    },
    rotate: {
        z: -25
    }
});

var trackable = new AR.ImageTrackable(tracker, "*", {
    drawables: {
        cam: [modelCar]
    },
});

onError: function onErrorFn(error) {
    alert(error);
}