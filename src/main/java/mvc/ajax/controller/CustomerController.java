package mvc.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.ajax.dto.CustomerDTO;
import mvc.ajax.service.CustomerService;
import mvc.ajax.service.CustomerServiceImpl;


public class CustomerController implements RestController {
   private CustomerService customerService = new CustomerServiceImpl();
   
   public void test(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("axios test입니다....");
		

		PrintWriter out = response.getWriter();
		 out.print("22222OK.");
		
		
	}
   
	/**
	 * 아디중복체크
	 *
	 * */
	public void idCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String id=request.getParameter("id");

		boolean isDuplicate = customerService.idCheck(id);

		String message;
		if(isDuplicate)
		{
			message="중복";
		}
		else
		{
			message="사용가능";
		}

		PrintWriter out = response.getWriter();
		out.print(message);
	}
	
	
	
	/**
	 * 등록하기
	 *  : 서비스 리턴한 int 그대로 응답
	 * */
	public void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		String tel=request.getParameter("tel");
		String addr=request.getParameter("addr");

		CustomerDTO customerDTO=new CustomerDTO(id, name, age, tel, addr);

		int result=customerService.insert(customerDTO);

		PrintWriter out = response.getWriter();
		out.print(result);
	}
	
	/**
	 * 전체검색
	 *  : List를 json으로 변환해서 응답
	 * */
	public void selectAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("CustomerController.selectAll");
		
		List<CustomerDTO> customerList=customerService.selectAll();

		Gson gson=new Gson();
		String jsonResponse=gson.toJson(customerList);


		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.print(jsonResponse);
		
	}
	
	/**
	 * 수정하기
	 *  : 수정된 결과 int형 응답
	 * */
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		String tel=request.getParameter("tel");
		String addr=request.getParameter("addr");

		CustomerDTO customerDTO=new CustomerDTO(id, name, age, tel, addr);
		System.out.println(customerDTO);

		int result=customerService.update(customerDTO);

		PrintWriter out = response.getWriter();
		out.print(result);
		
	}

	/*
	아이디로 고객 정보 가지고 오기(추가)
	 */
	public void selectById(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		String id=request.getParameter("id");

		CustomerDTO customerDTO=customerService.selectById(id);

		Gson gson=new Gson();
		String jsonResponse=gson.toJson(customerDTO);

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.print(jsonResponse);
	}
	
	/**
	 * 삭제하기 
	 *  : 삭제된 결과 int형 응답
	 * */
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String id=request.getParameter("id");

		int result=customerService.delete(id);

		System.out.println("result = " + result);

		PrintWriter out=response.getWriter();
		out.print(result);
	}

}











