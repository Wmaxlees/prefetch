.main:  movl	4(%esp), %ecx
	andl	$-16, %esp
	pushl	-4(%ecx)
	pushl	%ebp
	movl	%esp, %ebp
	pushl	%edi
	pushl	%ecx
	subl	$208, %esp
	movl	%gs:20, %eax
	movl	%eax, -12(%ebp)
	xorl	%eax, %eax
	;leal	-212(%ebp), %edx
	movl	$0, %eax
	movl	$50, %ecx
	movl	%edx, %edi
	rep stosl
	movl	$0, -216(%ebp)
	jmp	.L2
.L3:    movl	-216(%ebp), %eax
	movl	-216(%ebp), %edx
	;movl	%edx, -212(%ebp,%eax,4)
	addl	$1, -216(%ebp)
.L2:    cmpl	$99, -216(%ebp)
	jle	.L3
	movl	$0, %eax
	movl	-12(%ebp), %ecx
	xorl	%gs:20, %ecx
	je	.L5
	call	__stack_chk_fail
.L5:    addl	$208, %esp
	popl	%ecx
	popl	%edi
	popl	%ebp
	leal	-4(%ecx), %esp
	ret
