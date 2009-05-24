var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"sl":21,"el":51,"methods":[{"sl":26,"el":49,"sc":2},{"sl":32,"el":35,"sc":4},{"sl":41,"el":44,"sc":4}],"name":"InteractionPanel","id":6592}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = $jsonSrcFileLines
