# @author Tarush Gupta
# @date 2025-12-04

	.data
	newline: .asciiz "\n"
	varignore: .word 0
	varn: .word 0
	
	.text
	.globl __start
__start:
	#block
	#block
	#assignment: n
	#number
	li $v0, 3
	la $t0 varn
	sw $v0 ($t0)
	#assignment: ignore
	#procedure call: print
	subu $sp $sp 4
	sw $ra ($sp)
	#number
	li $v0, 5
	subu $sp $sp 4
	sw $v0 ($sp)
	jal procprint
	lw $t0 ($sp)
	addu $sp $sp 4
	lw $ra ($sp)
	addu $sp $sp 4
	la $t0 varignore
	sw $v0 ($t0)
	#writeln
	#variable: n
	la $t0 varn
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	li $v0, 10
	syscall
	
	#procedure declaration: print
procprint:
	subu $sp $sp 4
	sw $zero ($sp)
	#writeln
	#variable: n
	lw $v0 4($sp)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	lw $v0 ($sp)
	addu $sp $sp 4
	jr $ra
	
