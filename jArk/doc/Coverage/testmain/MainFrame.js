var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"sl":13,"el":80,"methods":[{"sl":15,"el":30,"sc":2},{"sl":32,"el":38,"sc":2},{"sl":34,"el":36,"sc":7},{"sl":43,"el":70,"sc":2},{"sl":53,"el":55,"sc":4},{"sl":62,"el":64,"sc":4},{"sl":72,"el":74,"sc":2},{"sl":76,"el":78,"sc":2}],"name":"MainFrame","id":5461}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = $jsonSrcFileLines
