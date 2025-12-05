# @author Tarush Gupta
# @date 2025-12-04

	.data
	newline: .asciiz "\n"
	
	.text
	.globl __start
__start:
	#block
	#writeln
	#number
	li $v0, 3
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	li $v0, 10
	syscall
	
