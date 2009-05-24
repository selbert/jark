var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"sl":22,"el":125,"methods":[{"sl":25,"el":81,"sc":2},{"sl":83,"el":85,"sc":2},{"sl":87,"el":89,"sc":2},{"sl":91,"el":93,"sc":2},{"sl":95,"el":97,"sc":2},{"sl":99,"el":124,"sc":2},{"sl":102,"el":119,"sc":4}],"name":"ImagesReference","id":5571}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = $jsonSrcFileLines
