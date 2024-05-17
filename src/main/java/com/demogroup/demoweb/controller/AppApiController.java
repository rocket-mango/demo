package com.demogroup.demoweb.controller;

//@RestController
//@RequiredArgsConstructor
//public class AppApiController {
//
//    private final DiseaseService diseaseService;
//    private final UserRepository userRepository;
//    private final FarmingInfoService farmingInfoService;
//
//    private final HomeService appService;
//
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    public static String convertEntityToJson(HomePageResponse obj){
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.addMixIn(User.class, UserApiController.passwordIgnore.class);
//        try{
//            return objectMapper.writeValueAsString(obj);
//        }catch (JsonProcessingException e){
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    //홈 화면에 띄울 정보를 보내는 컨트롤러. 기상청 정보와 사용자의 마이망고리스트를 보내야 한다.
//
//    @GetMapping("/api/home")
//    public ResponseEntity<String> homePage(){
//        //사용자 찾기
//        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = principal.getUsername();
//        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException());
//
//        try {
//            //기상청 날씨 정보
//            WeatherDto weather = appService.getWeather();
//
//            //사용자의 마이망고리스트
//            List<Mango> mangoList = diseaseService.mangoList(username);
//            List<FarmingInfo> list = farmingInfoService.findForHomeInfos();
//
//            HomePageResponse obj = HomePageResponse.of(mangoList,user,weather,list);
//
//            String response = convertEntityToJson(obj);
//
//            return ResponseEntity.ok().body(response);
//
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail get weather information");
//        }
//    }
//
//
//    //사용자의 마이망고리스트
//
//
//    abstract class passwordIgnore {
//        @JsonIgnore
//        private String password;
//    }
//}
