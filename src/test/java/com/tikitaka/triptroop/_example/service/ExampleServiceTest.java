package com.tikitaka.triptroop._example.service;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExampleServiceTest {

    // @Autowired
    // private ExampleRepository exampleRepository;

    // @DisplayName("기본 테스트")
    // @Test
    // void 테스트메서드명_성공() {
    //     Long exampleId = 15L
    //     Example foundExample = exampleRepository.findById(menuCode);
    //     assertNotNull(foundExample);
    //     System.out.println(foundMenu);
    // }

    /**
     * @ParameterizedTest
     * @MethodSource()
     * 파라미터가 있을 때
     */
    // private static Stream<Arguments> getExampleInfo() {
    //     return Stream.of(
    //             Arguments.of(111, "스테이크 크림 파스타", 9800, "Y")
    //     );
    // }
    // @DisplayName("파라미터가 있는 경우")
    // @ParameterizedTest
    // @MethodSource("getCategoryInfo")
    // void 테스트메서드명_성공(int categoryCode, String categoryName, Integer refCategoryCode) {
    //     // given
    //     Category category = new Category(
    //             categoryCode,
    //             categoryName,
    //             refCategoryCode
    //     );
    //
    //     // when
    //     biDirectionService.registCategory(category);
    //
    //     // then
    //     Category foundCategory = biDirectionService.findCategory(categoryCode);
    //     Assertions.assertNotNull(foundCategory);
    // }
}
