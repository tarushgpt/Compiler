# @author Tarush Gupta
# @date 2025-11-29

	.data
	
	.text
	.globl main
main:
	#block
	#writeln
	#number
	li $v0, 3
	move $a0, $v0
	li $v0, 1
	syscall
	li $a0, 10
	li $v0, 11
	syscall
	li $v0, 10
	syscall
