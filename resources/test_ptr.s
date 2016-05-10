main:
.LFB2:
	leal	4(%esp), %ecx
	andl	$-16, %esp
	pushl	-4(%ecx)
	pushl	%ebp
	movl	%esp, %ebp
	pushl	%ecx
	subl	$20, %esp
	subl	$12, %esp
	pushl	$2000000
	call	malloc
	addl	$16, %esp
	movl	%eax, -12(%ebp)
	movl	$0, -16(%ebp)
	jmp	.L2
.L3:
	movl	-16(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	-12(%ebp), %eax
	addl	%eax, %edx
	movl	-16(%ebp), %eax
	movl	%eax, (%edx)
	addl	$1, -16(%ebp)
.L2:
	cmpl	$499999, -16(%ebp)
	jle	.L3
	movl	$0, %eax
	movl	-4(%ebp), %ecx
	leave
	leal	-4(%ecx), %esp
	ret
.LFE2:
