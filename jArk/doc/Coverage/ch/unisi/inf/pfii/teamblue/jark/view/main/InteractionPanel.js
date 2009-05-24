var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"sl":25,"el":67,"methods":[{"sl":31,"el":65,"sc":2},{"sl":39,"el":46,"sc":4},{"sl":42,"el":44,"sc":9},{"sl":52,"el":60,"sc":4},{"sl":55,"el":57,"sc":9}],"name":"InteractionPanel","id":53}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = $jsonSrcFileLines
