package hello.hellospring.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemberServiceTest {

	MemberService memberService;
	MemoryMemberRepository memberRepository;

	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}

	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}

	@Test
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("spring");

		//when
		Long savedId = memberService.join(member);

		//then
		Member findMember = memberService.findOne(savedId).get();
		assertThat(findMember).isEqualTo(member);
	}

	@Test
	public void 중복_회원_예외() throws Exception {
		//given
		Member member1 = new Member();
		member1.setName("spring");
		memberService.join(member1);

		Member member2 = new Member();
		member2.setName("spring");

		//when
		IllegalStateException e = assertThrows(IllegalStateException.class,
			() -> memberService.join(member2));

		//then
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
	}

	@Test
	void findMembers() {
	}

	@Test
	void findOne() {
	}
}