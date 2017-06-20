Feature: Regions by point

  Scenario: a point inside with just one region
    Given the region with this rectangles:
      | 0 | 1 | 5 | 6 |
    And a point (2, 3)
    When get rectangles by point
    Then should be exist exactly 1 rectangles
    And should have this rectangles:
      | 0 | 1 | 5 | 6 |

  Scenario: P = (5,2) -> [F]
    Given the region with this rectangles:
      | 0 | 0 | 2 | 3 | A |
      | 0 | 3 | 2 | 5 | B |
      | 1 | 2 | 4 | 4 | C |
      | 2 | 0 | 4 | 3 | D |
      | 2 | 3 | 6 | 5 | E |
      | 3 | 0 | 6 | 3 | F |
    And a point (5, 2)
    When get rectangles by point
    Then should be exist exactly 1 rectangles
    And should have this rectangles:
      | 3 | 0 | 6 | 3 | F |

  Scenario: P = (0,0) -> [A]
    Given the region with this rectangles:
      | 0 | 0 | 2 | 3 | A |
      | 0 | 3 | 2 | 5 | B |
      | 1 | 2 | 4 | 4 | C |
      | 2 | 0 | 4 | 3 | D |
      | 2 | 3 | 6 | 5 | E |
      | 3 | 0 | 6 | 3 | F |
    And a point (0, 0)
    When get rectangles by point
    Then should be exist exactly 1 rectangles
    And should have this rectangles:
      | 0 | 0 | 2 | 3 | A |

  Scenario: P = (6,6) -> []
    Given the region with this rectangles:
      | 0 | 0 | 2 | 3 | A |
      | 0 | 3 | 2 | 5 | B |
      | 1 | 2 | 4 | 4 | C |
      | 2 | 0 | 4 | 3 | D |
      | 2 | 3 | 6 | 5 | E |
      | 3 | 0 | 6 | 3 | F |
    And a point (6, 6)
    When get rectangles by point
    Then should be exist exactly 0 rectangles

  Scenario: P = (6,5) -> [E]
    Given the region with this rectangles:
      | 0 | 0 | 2 | 3 | A |
      | 0 | 3 | 2 | 5 | B |
      | 1 | 2 | 4 | 4 | C |
      | 2 | 0 | 4 | 3 | D |
      | 2 | 3 | 6 | 5 | E |
      | 3 | 0 | 6 | 3 | F |
    And a point (6, 5)
    When get rectangles by point
    Then should be exist exactly 1 rectangles
    And should have this rectangles:
      | 2 | 3 | 6 | 5 | E |

  Scenario: P = (3,1) -> [D,F]
    Given the region with this rectangles:
      | 0 | 0 | 2 | 3 | A |
      | 0 | 3 | 2 | 5 | B |
      | 1 | 2 | 4 | 4 | C |
      | 2 | 0 | 4 | 3 | D |
      | 2 | 3 | 6 | 5 | E |
      | 3 | 0 | 6 | 3 | F |
    And a point (3, 1)
    When get rectangles by point
    Then should be exist exactly 2 rectangles
    And should have this rectangles:
      | 2 | 0 | 4 | 3 | D |
      | 3 | 0 | 6 | 3 | F |

  Scenario: P = (1,4) -> [B,C]
    Given the region with this rectangles:
      | 0 | 0 | 2 | 3 | A |
      | 0 | 3 | 2 | 5 | B |
      | 1 | 2 | 4 | 4 | C |
      | 2 | 0 | 4 | 3 | D |
      | 2 | 3 | 6 | 5 | E |
      | 3 | 0 | 6 | 3 | F |
    And a point (1, 4)
    When get rectangles by point
    Then should be exist exactly 2 rectangles
    And should have this rectangles:
      | 0 | 3 | 2 | 5 | B |
      | 1 | 2 | 4 | 4 | C |

  Scenario: P = (4,3) -> [D,E,F]
    Given the region with this rectangles:
      | 0 | 0 | 2 | 3 | A |
      | 0 | 3 | 2 | 5 | B |
      | 1 | 2 | 4 | 4 | C |
      | 2 | 0 | 4 | 3 | D |
      | 2 | 3 | 6 | 5 | E |
      | 3 | 0 | 6 | 3 | F |
    And a point (4, 3)
    When get rectangles by point
    Then should be exist exactly 4 rectangles
    And should have this rectangles:
      | 1 | 2 | 4 | 4 | C |
      | 2 | 0 | 4 | 3 | D |
      | 2 | 3 | 6 | 5 | E |
      | 3 | 0 | 6 | 3 | F |

  Scenario: P = (2,3) -> [A,B,C,D,E]
    Given the region with this rectangles:
      | 0 | 0 | 2 | 3 | A |
      | 0 | 3 | 2 | 5 | B |
      | 1 | 2 | 4 | 4 | C |
      | 2 | 0 | 4 | 3 | D |
      | 2 | 3 | 6 | 5 | E |
      | 3 | 0 | 6 | 3 | F |
    And a point (2, 3)
    When get rectangles by point
    Then should be exist exactly 5 rectangles
    And should have this rectangles:
      | 0 | 0 | 2 | 3 | A |
      | 0 | 3 | 2 | 5 | B |
      | 1 | 2 | 4 | 4 | C |
      | 2 | 0 | 4 | 3 | D |
      | 2 | 3 | 6 | 5 | E |

  Scenario: P = (3,3) -> [C,D,E,F]
    Given the region with this rectangles:
      | 0 | 0 | 2 | 3 | A |
      | 0 | 3 | 2 | 5 | B |
      | 1 | 2 | 4 | 4 | C |
      | 2 | 0 | 4 | 3 | D |
      | 2 | 3 | 6 | 5 | E |
      | 3 | 0 | 6 | 3 | F |
    And a point (3, 3)
    When get rectangles by point
    Then should be exist exactly 4 rectangles
    And should have this rectangles:
      | 1 | 2 | 4 | 4 | C |
      | 2 | 0 | 4 | 3 | D |
      | 2 | 3 | 6 | 5 | E |
      | 3 | 0 | 6 | 3 | F |
