package com.tikitaka.triptroop.admin.controller;

import com.tikitaka.triptroop.admin.dto.request.AdminUserSaveRequest;
import com.tikitaka.triptroop.admin.dto.response.AdminUserDetailResponse;
import com.tikitaka.triptroop.admin.dto.response.AdminUserResponse;
import com.tikitaka.triptroop.admin.service.AdminUserService;
import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.common.page.PageResponse;
import com.tikitaka.triptroop.common.page.Pagination;
import com.tikitaka.triptroop.common.page.PagingButtonInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/users")
public class AdminUserController {

    private final AdminUserService adminUserService;

    /* 1. 관리자 회원 관리 - 회원 목록 조회 */
    @GetMapping("")
    public ResponseEntity<ApiResponse<PageResponse>> getUserList(
            @RequestParam(defaultValue = "1", name = "page") final Integer page
    ) {
        final Page<AdminUserResponse> adminUserResponses = adminUserService.findAdminUsers(page);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(adminUserResponses);
        final PageResponse pagingResponse = PageResponse.of(adminUserResponses.getContent(), pagingButtonInfo);
        return ResponseEntity.ok(ApiResponse.success("회원 목록 조회에 성공 하였습니다.", pagingResponse));
    }

    /* 2. 관리자 회원 관리 - 회원 상세 조회 */
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUserDetail(@PathVariable final Long userId) {
        final AdminUserDetailResponse adminUserDetailResponse = adminUserService.findAdminUserDetail(userId);
        return ResponseEntity.ok(ApiResponse.success("회원 상세 조회에 성공 하였습니다.", adminUserDetailResponse));
    }

    /* 3. 관리자 회원 관리 - 회원 등록 */
//    @PostMapping("")
//    public ResponseEntity<ApiResponse<Long>> saveUser(@RequestPart("adminUserSaveRequest") final AdminUserSaveRequest adminUserSaveRequest,
//                                                      @RequestPart(value = "profileImage", required = false) MultipartFile profileImage) {
//        final Long userId = adminUserService.registerAdminUser(adminUserSaveRequest, profileImage);
//        return ResponseEntity.ok(ApiResponse.success("회원 등록에 성공 하였습니다.", userId));
//    }

    @PostMapping("/api/v1/admin/users")
    public ResponseEntity<?> registerUser(@ModelAttribute AdminUserSaveRequest adminUserSaveRequest,
                                          @RequestParam("file") MultipartFile file) {
        // 파일과 기타 데이터 처리 로직
        try {
            // 파일 저장 로직
            if (!file.isEmpty()) {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                // 파일 저장 경로 설정 및 저장
                System.out.println("fileName : " + fileName);
            }

            // 기타 데이터 처리 로직
            // ...

            return ResponseEntity.ok().body("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to parse multipart servlet request");
        }
    }





    /* 4. 관리자 회원 관리 - 회원 수정 */
//    @PutMapping("/{userId}")
//    public ResponseEntity<ApiResponse> updateUser(@PathVariable final Long userId,
//                                                  @RequestPart("adminUserUpdateRequest") @Valid final AdminUserUpdateRequest adminUserUpdateRequest,
//                                                  @RequestPart(value = "profileImage", required = false) MultipartFile profileImage
//    ) {
//
//        System.out.println("컨트롤러단 userId : " + userId + ", adminUserUpdateRequest : " + adminUserUpdateRequest + ", profileImage : " + profileImage);
//        //final AdminUserUpdateRequest updateUser = adminUserService.updateAdminUser(userId, adminUserUpdateRequest, profileImage);
//        System.out.println("컨트롤러단 반환값 : " + updateUser);
//        return ResponseEntity.ok(ApiResponse.success("회원 수정에 성공 하였습니다.", updateUser));
//
//    }

    /* 5. 관리자 회원 관리 - 회원 삭제 */
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> removeUser(@PathVariable final Long userId) {
        adminUserService.deleteAdminUser(userId);
        return ResponseEntity.ok(ApiResponse.success("회원 삭제에 성공 하였습니다."));
    }
}
