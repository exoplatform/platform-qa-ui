report({
  "testSuite": "BackstopJS",
  "tests": [
    {
      "pair": {
        "reference": "../bitmaps_reference/backstop_default_BackstopJS_Homepage_0__0_desktop.png",
        "test": "../bitmaps_test/20200220-163109/backstop_default_BackstopJS_Homepage_0__0_desktop.png",
        "selector": "",
        "fileName": "backstop_default_BackstopJS_Homepage_0__0_desktop.png",
        "label": "BackstopJS Homepage",
        "requireSameDimensions": true,
        "misMatchThreshold": 0.1,
        "expect": 0,
        "viewportLabel": "desktop",
        "engineErrorMsg": "Protocol error (Page.navigate): Invalid parameters url: string value expected",
        "error": "Test file not found /home/exo/exoK/platform-qa-ui/backstop/eXoPLFtest_eng/backstop_data/bitmaps_test/20200220-163109/backstop_default_BackstopJS_Homepage_0__0_desktop.png"
      },
      "status": "fail"
    },
    {
      "pair": {
        "reference": "../bitmaps_reference/backstop_default_BackstopJS_Homepage_0__1_phone.png",
        "test": "../bitmaps_test/20200220-163109/backstop_default_BackstopJS_Homepage_0__1_phone.png",
        "selector": "",
        "fileName": "backstop_default_BackstopJS_Homepage_0__1_phone.png",
        "label": "BackstopJS Homepage",
        "requireSameDimensions": true,
        "misMatchThreshold": 0.1,
        "expect": 0,
        "viewportLabel": "phone",
        "engineErrorMsg": "Protocol error (Page.navigate): Invalid parameters url: string value expected",
        "diff": {
          "isSameDimensions": true,
          "dimensionDifference": {
            "width": 0,
            "height": 0
          },
          "misMatchPercentage": "0.00"
        }
      },
      "status": "pass"
    },
    {
      "pair": {
        "reference": "../bitmaps_reference/backstop_default_BackstopJS_Homepage_0__2_tablet.png",
        "test": "../bitmaps_test/20200220-163109/backstop_default_BackstopJS_Homepage_0__2_tablet.png",
        "selector": "",
        "fileName": "backstop_default_BackstopJS_Homepage_0__2_tablet.png",
        "label": "BackstopJS Homepage",
        "requireSameDimensions": true,
        "misMatchThreshold": 0.1,
        "expect": 0,
        "viewportLabel": "tablet",
        "engineErrorMsg": "Protocol error (Page.navigate): Invalid parameters url: string value expected",
        "diff": {
          "isSameDimensions": true,
          "dimensionDifference": {
            "width": 0,
            "height": 0
          },
          "misMatchPercentage": "0.00"
        }
      },
      "status": "pass"
    }
  ],
  "id": "backstop_default"
});